class Requests {
    constructor(email, date, amount, description, requestID){
        this._email = email;
        this._date = date;
        this._amount = amount;
        this._description = description;
        this._requestID = requestID;
    }
    get email(){
        return this._email;
    }
    get date(){
        return this._date;
    }
    get amount(){
        return this._amount;
    }
    get description(){
        return this._description;
    }
    get requestID(){
        return this._requestID;
    }
    set email(email){
        this._email = email;
    }
    set date(date){
        this._date = date;
    }
    set amount(amount){
        this._amount = amount;
    }
    set description(description){
        this._description = description;
    }
    set requestID(requestID){
        this._requestID = requestID;
    }

    toJSON(){
        return{
            email: this._email,
            date: this._date,
            amount: this._amount,
            description: this._description,
            requestID: this._requestID,
        };
    }
}

class Decided {
    constructor(managerEmail, date, approved, decisionID, requestID){
        this._managerEmail = managerEmail;
        this._date = date;
        this._approved = approved;
        this._decisionID = decisionID;
        this._requestID = requestID;
    }

    get managerEmail(){
        return this._managerEmail;
    }
    get date(){
        return this._date;
    }
    get approved(){
        return this._approved;
    }
    get decisionID(){
        return this._decisionID;
    }
    get requestID(){
        return this._requestID
    }
    set managerEmail(email){
        this._managerEmail = email;
    }
    set date(date){
        this._date = date;
    }
    set approved(approved){
        this._approved = approved;
    }
    set decisionID(decisionID){
        this._decisionID = decisionID;
    }
    set requestID(requestID){
        this._requestID = requestID;
    }

    toJOSN(){
        return{
            managerEmail: this._managerEmail,
            date: this._date,
            approved: this._approved,
            decisionID: this._decisionID,
            requestID: this._requestID,
        };
    }
}

class RequestDisision{
    constructor(employeeEmailRD, dateRD, amountRD, descriptionRD, statusRD, managerEmailRD){
        this._employeeEmailRD = employeeEmailRD;
        this._dateRD = dateRD;
        this._amountRD = amountRD;
        this._descriptionRD = descriptionRD;
        this._statusRD = statusRD;
        this._managerEmailRD = managerEmailRD;
    }
    get employeeEmailRD(){
        return this._employeeEmailRD;
    }
    get dateRD(){
        return this._dateRD;
    }
    get amountRD(){
        return this._amountRD;
    }
    get descriptionRD(){
        return this._descriptionRD;
    }
    get statusRD(){
        return this._statusRD
    }
    get managerEmailRD(){
        return this._managerEmail
    }
    set employeeEmailRD(email){
        this._employeeEmailRD = email;
    }
    set dateRD(date){
        this._dateRD = date;
    }
    set amountRD(amount){
        this._amountRD = amount;
    }
    set descriptionRD(description){
        this._descriptionRD = description;
    }
    set statusRD(status){
        this._statusRD = status;
    }
    set managerEmailRD(email){
        this._managerEmail = email;
    }
}


let requestDisisionList = [];

const appRoot = document.querySelector('#wholeWindow');

const disisionContainer = appRoot.querySelector('#reimbursementTableRow');

const newRequestButton = document.querySelector('#newRequestButton');
    
const mergeRequestDesision = (req, des) => {

    req.forEach(a => {
        let checkStatus = false;

        for(let i = 0; i < des.length; i++) {

            if(a.requestID == des[i].requestID){

                let status = des[i].approved ? 'Approved':'Declined';

                console.log(`request: ${a.email} ${a.date} ${a.amount} ${a.description}`);
                console.log(`decidion: ${des[i].approved} ${des[i].managerEmail}`);

                requestDisisionList.push(new RequestDisision(a.email, a.date, a.amount, a.description,
                    status, des[i].managerEmail));

                    checkStatus = true;

                    break;
                    
            }
        }
        if(!checkStatus){
            console.log(`request: ${a.email} ${a.date} ${a.amount} ${a.description}`);
            requestDisisionList.push(new RequestDisision(a.employeeEmail, a.date, a.amount, a.description,
                'Pending', 'Pending'));
        }
    });

    }



    const renderRequestTableData = (container) => {
        container.innerHTML = '';
        requestDisisionList.forEach(r => {
            const newDesision = document.createElement('tr');
            const rEmpEmail = document.createElement('td');
            const rDate = document.createElement('td');
            const rAmount = document.createElement('td');
            const rDescription = document.createElement('td');
            const rStatus = document.createElement('td');
            const rManager = document.createElement('tr');

            rEmpEmail.innerText = r.employeeEmailRD
            rDate.innerText = r.dateRD;
            rAmount.innerText = r.amountRD;
            rDescription.innerText = r.descriptionRD;
            rStatus.innerText = r.statusRD;
            rManager.innerText = r.managerEmailRD

            newDesision.append(rEmpEmail);
            newDesision.append(rDate);
            newDesision.append(rAmount);
            newDesision.append(rDescription);
            newDesision.append(rStatus);
            newDesision.append(rManager);
            
            container.append(newDesision);
        });
    }
    
    renderRequestTableData(disisionContainer);
 
document.addEventListener('DOMContentLoaded', () => {
    ;(async () => {
        console.log('after the DOMContentLoaded')
       
        const resp1 = await fetch(
            'http://localhost:8080/ReimbursementSystem/ManagerPortal'
            )
        const reims = await resp1.json()

        ;console.log(reims);

        const resp2 = await fetch(
              'http://localhost:8080/ReimbursementSystem/ManagerPortal2'
            )
        const resolved = await resp2.json()

        console.log(resolved);

      //  <!--requestDisisionList = [ ...reims, ...resolved ] -->
        
        
      //  console.log(requestDisisionList)

        mergeRequestDesision(reims, resolved);

        console.log('after populating the master list')

        console.log(requestDisisionList);

        renderRequestTableData(disisionContainer);

        })()
    });
        
newRequestButton.addEventListener('click', () =>{
    const newAmount = document.querySelector('#amount').value;

    if(newAmount){

        const newDescription = document.querySelector('#description').value;

        const newRequestForm = new Requests(newAmount, newDescription);

        fetch('http://localhost:8080/ReimbursementSystem/Reimbursements', {
            method: 'Post',
            header: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newRequestForm),
        })
        .then(resp => resp.json())

        .then((newRequestWithID) => {

            requestDisisionList.push(new RequestDisision(newRequestWithID.employeeEmail, 
                newRequestWithID.date, newRequestWithID.amount, newRequestWithID.description,
                'Pending', 'Pending'));

                console.log('')

                renderRequestTableData(disisionContainer);

                document.querySelector('#amount').value = '';

                document.querySelector('#description').value = '';
        });
    }
 })


