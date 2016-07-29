// @flow
import path from 'path';

/**
 * Controller for managing the web-interface.
 */
class InterfaceController {

  static mount(app): void {
    // [GET] /
    app.get(
      '/',
      this.renderInterface,
    );
  }

  /**
   * Renders the index.html
   */
  static renderInterface(req, res) {
    const indexHtml = path.join(__dirname, '../public/index.html');
    console.log(indexHtml);
    return res.sendFile(indexHtml);
  }
}

export default InterfaceController;
