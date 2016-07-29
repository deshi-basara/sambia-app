export class Item {
  public id: string;
  public name: string;
  public image: any;

  constructor(
    _id: string,
    _name: string,
    translation: string,
    image: string,
    enabled: boolean
  ) {
    this.id = _id;
    this.name = _name;
  }

}
