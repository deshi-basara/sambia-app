import { Item } from './item';

export class Activity {
  public id: string;
  public name: string;
  public items: Item[] = [];
  public image: any;
  public enabled: boolean;

  constructor(
    _id: string,
    _name: string,
    translation: string,
    image: string,
    _items: Item[],
    _enabled: boolean
  ) {
    this.id = _id;
    this.name = _name;
    this.items = _items;
    this.enabled = _enabled;
  }

}
