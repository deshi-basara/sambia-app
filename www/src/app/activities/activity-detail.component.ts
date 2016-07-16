import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ActivityService } from '../shared/services/activity.service';
import { ImageUpload } from '../shared/directives/image-upload.directive';

import { Group } from './group';
import { Activity } from './activity';
import { Item } from './item';

@Component({
  moduleId: module.id,
  selector: 'app-activity-detail',
  templateUrl: 'activity-detail.component.html',
  styleUrls: ['activity-detail.component.css'],
  directives: [ImageUpload],
  providers: [ActivityService]
})
export class ActivityDetailComponent implements OnInit {
  private subscribedRoute: any;

  /**
   * Attributes
   */
  showModal: boolean = false;
  updateIndex: number;
  groupModel = new Group(
    null,
    '',
    '',
    '',
    [],
    false
  );
  activityModel = new Activity(
    null,
    '',
    '',
    '',
    [],
    false
  );

  constructor(
    private route: ActivatedRoute,
    private activityService: ActivityService
  ) { }

  /**
   * Initialize component after Angular initializes the data-bound input
   * properties.
   */
  ngOnInit() {
    // retrieve route-parameters by subscribing to the activeRoute-service
    this.subscribedRoute = this.route.params.subscribe(
      params => {
        let id = params['id'];
        this.activityService.getActivityGroup(id)
          .subscribe(
            activityGroup => {
              this.groupModel = activityGroup[0];
            },
            error => {
              // TODO: Implement error feedback
              console.log(error);
            }
          );
      }
    );
  }

  /**
   * Is called when the updated form is submitted.
   * Makes an update-request to the server.
   *
   * @return {[type]} [description]
   */
  onSubmit() {
    // cast strings from select-option into boolean
    this.groupModel.enabled = (this.groupModel.enabled == 'true') ? true : false;

    // submit to server
    this.activityService.putActivityGroup(this.groupModel)
      .subscribe(
        activityGroup => {
          console.log(activityGroup);
        },
        error => {
          console.log(error);
        }
      );
  }

  /**
   * Adds a newly created activity from the activity-modal-form to an activity group.
   */
  onAdd() {
    // shall we update an already created activity?
    if (this.updateIndex !== -1) {
      // old activity, need to update
      this.groupModel.activities[this.updateIndex] = this.activityModel;
    }
    else {
      // new activity, add actvityModel to group
      this.groupModel.activities.push(this.activityModel);
    }

    // hide modal
    this.toggleModal(null);
  }

  onAddItem() {
    // cerate new item-object and add it to selected activity
    let item = new Item(
      null,
      '',
      '',
      '',
      false
    );

    this.activityModel.items.push(item);
  }

  /**
   * Toggles the modal for adding an activity to a group.
   * Modal can be toggled in editing and creation mode, according to handed
   * parameter.
   *
   * @param  {number} _index [If an activityModel-index is handed, modal is started
   *                          in editing mode. Otherwise an empty modal will be opened]
   */
  toggleModal(_index) {
    // shall we open the modal in editing mode?
    if (_index !== null) {
      // set clicked activity to modal-activity
      this.activityModel = this.groupModel.activities[_index];
      this.updateIndex = _index;
    }
    else {
      // no activity handed, open empty modal
      this.activityModel = new Activity(
        null,
        '',
        '',
        '',
        [],
        false
      );
      this.updateIndex = -1;
    }

    this.showModal = this.showModal ? false : true;
  }

}
