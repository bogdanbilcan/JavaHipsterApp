import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'notif-template',
        loadChildren: () => import('./notif-template/notif-template.module').then(m => m.JavaHipsterAppNotifTemplateModule)
      },
      {
        path: 'dealer',
        loadChildren: () => import('./dealer/dealer.module').then(m => m.JavaHipsterAppDealerModule)
      },
      {
        path: 'portofoliu',
        loadChildren: () => import('./portofoliu/portofoliu.module').then(m => m.JavaHipsterAppPortofoliuModule)
      },
      {
        path: 'stoc',
        loadChildren: () => import('./stoc/stoc.module').then(m => m.JavaHipsterAppStocModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JavaHipsterAppEntityModule {}
