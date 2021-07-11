class Requests {
    constructor(amount, description){
        this._amount = amount;
        this._description = description;
    }
    get amount(){
        return this._amount;
    }
    get description(){
        return this._description;
    }
    set amount(amount){
        this._amount = amount;
    }
    set description(description){
        this._description = description;
    }


    toJSON(){
        return{
            date: this._date,
            amount: this._amount,
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

    req.forEach(a => {
        let checkStatus = false;
        for(let i = 0; i < des.length; i++) {
            if(a.requestID == des[i].requestID){

                let status = des[i].approved ? 'Approved':'Declined';

                requestDisisionList.push(new RequestDisision(a.email, a.date, a.amount, a.description,
                    status));

                    checkStatus = true;

                    break;
                    
            }
        }
        if(!checkStatus){
            requestDisisionList.push(new RequestDisision(a.employeeEmail, a.date, a.amount, a.description,
                'Pending'));
        }
    });
    }


    let requestDisisionList = [];

    const appRoot = document.querySelector('#wholeWindow');

    const requestContainer = appRoot.querySelector('#requestTableRow');

    const newRequestButton = document.querySelector('#newRequestButton');

    const renderRequestTableData = (container) => {
        container.innerHTML = '';
        requestDisisionList.forEach(r => {
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
    
 
document.addEventListener('DOMContentLoaded', () => {
    ;(async () => {
        console.log('after the DOMContentLoaded')
       
        const resp1 = await fetch(
            'http://localhost:8080/ReimbursementSystem/Reimbursements'
            )
        const reims = await resp1.json()

        ;console.log(reims);

        const resp2 = await fetch(
              'http://localhost:8080/ReimbursementSystem/EmployeeDecided'
            )
        const resolved = await resp2.json()

        console.log(resolved);

      //  <!--requestDisisionList = [ ...reims, ...resolved ] -->
        
        console.log('after populating the master list')
      //  console.log(requestDisisionList)

        mergeRequestDesision(reims, resolved);

        console.log(requestDisisionList);

        renderRequestTableData(requestContainer);

        })()
    });
        
newRequestButton.addEventListener('click', () =>{
    const newAmount = document.querySelector('#amount').value;
    if(newAmount){
        const newRequestForm = new Requests(newAmount, document.querySelector('#description'));
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
                'Pending'));

                renderRequestTableData(requestContainer);

                document.querySelector('#amount').value = '';

                document.querySelector('#description').value = '';
        });
    }
})



