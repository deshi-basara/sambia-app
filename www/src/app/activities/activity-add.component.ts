import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { SnackbarComponent } from '../snackbar';
import { ActivityService } from '../shared/services/activity.service';
import { ImageUpload } from '../shared/directives/image-upload.directive';

import { Group } from './group';
import { Activity } from './activity';
import { Item } from './item';

@Component({
  moduleId: module.id,
  selector: 'app-activity-add',
  templateUrl: 'activity-add.component.html',
  styleUrls: ['activity-add.component.css'],
  directives: [ImageUpload, SnackbarComponent],
  providers: [ActivityService]
})
export class ActivityAddComponent implements OnInit {
  @ViewChild(SnackbarComponent) snackbar: SnackbarComponent;

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
    private router: Router,
    private activityService: ActivityService
  ) { }

  /**
   * Initialize component after Angular initializes the data-bound input
   * properties.
   */
  ngOnInit() {
  }

  onSubmit() {
    // cast strings from select-option into boolean
    this.groupModel.enabled = (this.groupModel.enabled == 'true') ? true : false;

    // was there at least on activity added

    // submit to server
    this.activityService.postActivityGroup(this.groupModel)
      .subscribe(
        activityGroup => {
          this.snackbar.showSnackbar('Activity-Group was successfully created.', false);

          setTimeout(() => {
            this.router.navigate(['/activities']);
          }, 2000);
        },
        error => {
          this.snackbar.showSnackbar(error, true);
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
