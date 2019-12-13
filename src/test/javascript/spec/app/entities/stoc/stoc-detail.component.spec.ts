import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JavaHipsterAppTestModule } from '../../../test.module';
import { StocDetailComponent } from 'app/entities/stoc/stoc-detail.component';
import { Stoc } from 'app/shared/model/stoc.model';

describe('Component Tests', () => {
  describe('Stoc Management Detail Component', () => {
    let comp: StocDetailComponent;
    let fixture: ComponentFixture<StocDetailComponent>;
    const route = ({ data: of({ stoc: new Stoc(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JavaHipsterAppTestModule],
        declarations: [StocDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(StocDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StocDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.stoc).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
