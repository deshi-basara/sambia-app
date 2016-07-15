import { Activity } from './activity';

export class Group {
  public id: string;
  public name: string;
  public translation: string = 'translation';
  public image: any;
  public activities: Activity[];
  public enabled: any = true; // html-select converts bool into string

  constructor(
    id: string,
    name: string,
    translation: string,
    image: string,
    _activities: Activity[],
    enabled: any
  ) {
    this.activities = _activities;
  }

}
