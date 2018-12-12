/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNeedNGRequestDetailComponent } from 'app/entities/energy-need-ng-request/energy-need-ng-request-detail.component';
import { EnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';

describe('Component Tests', () => {
    describe('EnergyNeedNGRequest Management Detail Component', () => {
        let comp: EnergyNeedNGRequestDetailComponent;
        let fixture: ComponentFixture<EnergyNeedNGRequestDetailComponent>;
        const route = ({ data: of({ energyNeedNGRequest: new EnergyNeedNGRequest(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNeedNGRequestDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyNeedNGRequestDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyNeedNGRequestDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyNeedNGRequest).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
