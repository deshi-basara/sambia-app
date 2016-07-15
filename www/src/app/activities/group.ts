import { Activity } from './activity';

export class Group {
  public id: string;
  public name: string;
  public translation: string = 'translation';
  public image: any;
  public activities: Activity[];
  public enabled: any = true; // html-select converts bool into string

  constructor(
    _id: string,
    _name: string,
    _translation: string,
    _image: string,
    _activities: Activity[],
    _enabled: any
  ) {
    this.id = _id;
    this.name = _name;
    this.translation = _translation;
    this.image = _image;
    this.activities = _activities;
    this.enabled = _enabled;
  }

}
