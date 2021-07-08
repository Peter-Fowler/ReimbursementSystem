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

}

const testRequest = new Requests('peter.fowler@revature.net', 
    '03-JUN-21', 200.86, 'An example to make sure everything works', 42);
    
    console.log(testRequest);