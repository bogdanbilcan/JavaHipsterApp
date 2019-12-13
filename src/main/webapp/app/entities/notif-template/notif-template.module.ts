import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JavaHipsterAppSharedModule } from 'app/shared/shared.module';
import { NotifTemplateComponent } from './notif-template.component';
import { NotifTemplateDetailComponent } from './notif-template-detail.component';
import { NotifTemplateUpdateComponent } from './notif-template-update.component';
import { NotifTemplateDeleteDialogComponent } from './notif-template-delete-dialog.component';
import { notifTemplateRoute } from './notif-template.route';

@NgModule({
  imports: [JavaHipsterAppSharedModule, RouterModule.forChild(notifTemplateRoute)],
  declarations: [NotifTemplateComponent, NotifTemplateDetailComponent, NotifTemplateUpdateComponent, NotifTemplateDeleteDialogComponent],
  entryComponents: [NotifTemplateDeleteDialogComponent]
})
export class JavaHipsterAppNotifTemplateModule {}
