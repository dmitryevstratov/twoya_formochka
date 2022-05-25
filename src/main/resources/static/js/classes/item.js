class Item {
    constructor(id, count) {
        this.id = id;
        this.count = count;
    }

    returnString() {
        return this.id + ":" + this.count + ";";
    }
}