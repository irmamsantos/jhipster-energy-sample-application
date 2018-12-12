/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyTimePeriodDataDetailComponent } from 'app/entities/energy-time-period-data/energy-time-period-data-detail.component';
import { EnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';

describe('Component Tests', () => {
    describe('EnergyTimePeriodData Management Detail Component', () => {
        let comp: EnergyTimePeriodDataDetailComponent;
        let fixture: ComponentFixture<EnergyTimePeriodDataDetailComponent>;
        const route = ({ data: of({ energyTimePeriodData: new EnergyTimePeriodData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyTimePeriodDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyTimePeriodDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyTimePeriodDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyTimePeriodData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
