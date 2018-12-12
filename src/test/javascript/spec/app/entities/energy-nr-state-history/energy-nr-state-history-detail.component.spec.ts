/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNRStateHistoryDetailComponent } from 'app/entities/energy-nr-state-history/energy-nr-state-history-detail.component';
import { EnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';

describe('Component Tests', () => {
    describe('EnergyNRStateHistory Management Detail Component', () => {
        let comp: EnergyNRStateHistoryDetailComponent;
        let fixture: ComponentFixture<EnergyNRStateHistoryDetailComponent>;
        const route = ({ data: of({ energyNRStateHistory: new EnergyNRStateHistory(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNRStateHistoryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyNRStateHistoryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyNRStateHistoryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyNRStateHistory).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
