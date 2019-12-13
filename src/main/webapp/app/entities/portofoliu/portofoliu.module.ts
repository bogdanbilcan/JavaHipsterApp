import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JavaHipsterAppSharedModule } from 'app/shared/shared.module';
import { PortofoliuComponent } from './portofoliu.component';
import { PortofoliuDetailComponent } from './portofoliu-detail.component';
import { portofoliuRoute } from './portofoliu.route';

@NgModule({
  imports: [JavaHipsterAppSharedModule, RouterModule.forChild(portofoliuRoute)],
  declarations: [PortofoliuComponent, PortofoliuDetailComponent],
  entryComponents: []
})
export class JavaHipsterAppPortofoliuModule {}
