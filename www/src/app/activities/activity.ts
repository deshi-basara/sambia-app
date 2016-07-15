import { Item } from './item';

export class Activity {
  public items: Item[];
  public image: any;
  public enabled: boolean;

  constructor(
    id: string,
    name: string,
    translation: string,
    image: string,
    items: Item[],
    enabled: boolean
  ) {
    this.items = [];
    this.enabled = enabled;
  }

}
