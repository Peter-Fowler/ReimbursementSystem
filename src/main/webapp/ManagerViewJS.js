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

const testRequest = new Requests('peter.fowler@revature.net', 
    '03-JUN-21', 200.86, 'An example to make sure everything works', 42);
    
    console.log(testRequest);

    const testDecided = new Decided('big.boss@revature.net',
    '03-JUN-21', true, 18, 42);

    console.log(testDecided);


