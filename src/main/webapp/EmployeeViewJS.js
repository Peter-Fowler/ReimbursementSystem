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


    const testRequest = new Requests('peter.fowler@revature.net', 
    '03-JUN-21', 200.86, 'An example to make sure everything works', 42);
    
    console.log(testRequest);

    let requestList = [];

    const appRoot = document.querySelector('#wholeWindow');
    const requestContainer = appRoot.querySelector('#requestTableRow');

    const renderRequestTableData = () => {
        requestContainer.innerHTML = '';
        requestList.forEach(r => {
            const newRequest = document.createElement('tr');
            const rDate = document.createElement('td');
            const rAmount = document.createElement('td');
            const rDescription = document.createElement('td');
            const rStatus = document.createElement('td');

            rDate.innerText = r.date;
            rAmount.innerText = r.amount;
            rDescription.innerText = r.description;
            rStatus.innerText = 'Pendding';

            newRequest.append(rDate);
            newRequest.append(rAmount);
            newRequest.append(rDescription);
            newRequest.append(rStatus);
            return newRequest;
        });
    }

/* document.addEventListener('DOMContentLoaded', () => {
    fetch('')
    .then(resp => resp.json())
    .then()
} */