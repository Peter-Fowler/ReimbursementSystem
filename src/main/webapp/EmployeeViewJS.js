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
    constructor(employeeEmailRD, dateRD, amountRD, descriptionRD, statusRD){
        this._employeeEmailRD = employeeEmailRD;
        this._dateRD = dateRD;
        this._amountRD = amountRD;
        this._descriptionRD = descriptionRD;
        this._statusRD = statusRD;
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
}


const mergeRequestDesision = (req, des) => {
    let status ='';

    if(des.approved != null || des.approved != undefined){
            status = des.approved ? 'Approved!':'Declined';
        }else{
            status = 'Pending';
        }
    return reqdes = new RequestDisision(req.email, req.date, req.amount, req.description,
        status);
    }

    const testRequest = new Requests('peter.fowler@revature.net',   //for testing
    '03-JUN-21', 200.86, 'An example to make sure everything works', 42);
    
    console.log(testRequest);                                       //for testing

    const testDecided = new Decided('big.boss@revature.net',        //for testing
    '03-JUN-21', true, 18, 42);

    console.log(testDecided);                                        //for testing

    let requestList = [];

    let decidedList = [];

    let requestDisisionList = [];

     let testX = mergeRequestDesision(testRequest, testDecided);

     console.log(testX);

    requestList.push(testX);//for testing

    console.log(requestList);//for testing

    const appRoot = document.querySelector('#wholeWindow');
    const requestContainer = appRoot.querySelector('#requestTableRow');

    const renderRequestTableData = (container) => {
        console.log('In the renderRequestTableData function');//for testing
        container.innerHTML = '';
        requestList.forEach(r => {
            console.log('In the forEach function');
            const newRequest = document.createElement('tr');
            const rDate = document.createElement('td');
            const rAmount = document.createElement('td');
            const rDescription = document.createElement('td');
            const rStatus = document.createElement('td');

            rDate.innerText = r.dateRD;
            rAmount.innerText = r.amountRD;
            rDescription.innerText = r.descriptionRD;
            rStatus.innerText = r.statusRD;

            newRequest.append(rDate);
            newRequest.append(rAmount);
            newRequest.append(rDescription);
            newRequest.append(rStatus);
            
            container.append(newRequest);
        });
    }

    console.log('Right before the renderRequestTableData function call');//for testing
    renderRequestTableData(requestContainer);

//   document.addEventListener('DOMContentLoaded', () => {

//      // const fetchPromise = new Promise()
//     fetch('http://localhost:8080/ReimbursementSystem/Reimbursements')
//     .then(resp => resp.json())
//     .then((requests) => {
//         requestList = requests;

//     });
//  });

//  document.addEventListener('DOMContentLoaded', () => {
//     fetch('http://localhost:8080/ReimbursementSystem/EmployeeDecided')
//     .then(resp => resp.json())
//     .then((decided) => {
//         decidedList = decided;

//     });
//  });

 
document.addEventListener('DOMContentLoaded', () => {
     async() => {
         
        console.log('after the DOMContentLoaded');
        
        const resp1 = await fetch('http://localhost:8080/ReimbursementSystem/Reimbursements');
        const reims = await resp1.json();
        const resp2 = await fetch('http://localhost:8080/ReimbursementSystem/EmployeeDecided');
        const resolved = await resp2.json();
        requestDisisionList = {...reims, ...resolved};

        console.log('after populating the master list');
        console.log(requestDisisionList);

}});

