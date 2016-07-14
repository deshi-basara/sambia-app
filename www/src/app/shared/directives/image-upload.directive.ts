import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[image-upload]'
})
export class ImageUpload {
  private el: HTMLElement;

  constructor(_el: ElementRef) {
    this.el = _el.nativeElement;
  }

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

      console.log(file);
      console.log(this.el);
      console.log(image);

      // set preview
      image.src = content.result;
    }

    // parse image into fileReader
    reader.readAsDataURL(_imageFile);
  }

}
