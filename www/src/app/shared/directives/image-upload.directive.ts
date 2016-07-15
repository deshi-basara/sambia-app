import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[image-upload]'
})
export class ImageUpload {
  private el: HTMLElement;

  constructor(_el: ElementRef) {
    this.el = _el.nativeElement;
  }

  @Input('image-upload') targetModel: any;

  /**
   * Opens a file-select-dialog on 'click'-event.
   */
  @HostListener('click') onSelectPhoto() {
    let input: HTMLElement = this.el.getElementsByTagName('input')[0];
    input.click();
  }

  /**
   * Is called whenever a new imagefile was selected.
   */
  @HostListener('change') onSelected() {
    let input: HTMLInputElement = this.el.getElementsByTagName('input')[0];
    let imageFile = input.files[0];

    // does a file exist?
    if (!imageFile) {
      return;
    }

    // read image
    this.readImage(imageFile);
  }

  readImage(_imageFile) {
    // initiate html5-filereader
    let reader: FileReader = new FileReader();
    reader.onload = file => {
      let image: HTMLImageElement = this.el.getElementsByTagName('img')[0];
      let content: any = file.target;

      // set preview
      image.src = content.result;

      // set file-content as image in model
      this.targetModel.image = content.result;
    }

    // parse image into fileReader
    reader.readAsDataURL(_imageFile);
  }

}
