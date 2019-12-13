import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JavaHipsterAppTestModule } from '../../../test.module';
import { PortofoliuDetailComponent } from 'app/entities/portofoliu/portofoliu-detail.component';
import { Portofoliu } from 'app/shared/model/portofoliu.model';

describe('Component Tests', () => {
  describe('Portofoliu Management Detail Component', () => {
    let comp: PortofoliuDetailComponent;
    let fixture: ComponentFixture<PortofoliuDetailComponent>;
    const route = ({ data: of({ portofoliu: new Portofoliu(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JavaHipsterAppTestModule],
        declarations: [PortofoliuDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PortofoliuDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PortofoliuDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.portofoliu).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
