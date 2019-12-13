import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JavaHipsterAppSharedModule } from 'app/shared/shared.module';
import { StocComponent } from './stoc.component';
import { StocDetailComponent } from './stoc-detail.component';
import { stocRoute } from './stoc.route';

@NgModule({
  imports: [JavaHipsterAppSharedModule, RouterModule.forChild(stocRoute)],
  declarations: [StocComponent, StocDetailComponent],
  entryComponents: []
})
export class JavaHipsterAppStocModule {}
