/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVoltageDataComponent } from 'app/entities/energy-voltage-data/energy-voltage-data.component';
import { EnergyVoltageDataService } from 'app/entities/energy-voltage-data/energy-voltage-data.service';
import { EnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';

describe('Component Tests', () => {
    describe('EnergyVoltageData Management Component', () => {
        let comp: EnergyVoltageDataComponent;
        let fixture: ComponentFixture<EnergyVoltageDataComponent>;
        let service: EnergyVoltageDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVoltageDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyVoltageDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyVoltageDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVoltageDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyVoltageData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyVoltageData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
