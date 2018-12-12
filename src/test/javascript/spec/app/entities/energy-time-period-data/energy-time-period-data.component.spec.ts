/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyTimePeriodDataComponent } from 'app/entities/energy-time-period-data/energy-time-period-data.component';
import { EnergyTimePeriodDataService } from 'app/entities/energy-time-period-data/energy-time-period-data.service';
import { EnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';

describe('Component Tests', () => {
    describe('EnergyTimePeriodData Management Component', () => {
        let comp: EnergyTimePeriodDataComponent;
        let fixture: ComponentFixture<EnergyTimePeriodDataComponent>;
        let service: EnergyTimePeriodDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyTimePeriodDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyTimePeriodDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyTimePeriodDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyTimePeriodDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyTimePeriodData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyTimePeriodData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
