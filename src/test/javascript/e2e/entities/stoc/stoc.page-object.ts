import { element, by } from 'protractor';

export class StocComponentsPage {
  title = element.all(by.css('jhi-stoc div h2#page-heading span')).first();

  async getTitle() {
    return this.title.getText();
  }
}
