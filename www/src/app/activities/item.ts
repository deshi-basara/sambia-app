export class Item {
  public name: string;
  public image: any;

  constructor(
    id: string,
    _name: string,
    translation: string,
    image: string,
    enabled: boolean
  ) {
    this.name = _name;
  }

}
