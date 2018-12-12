/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyElectricityDataComponent } from 'app/entities/energy-electricity-data/energy-electricity-data.component';
import { EnergyElectricityDataService } from 'app/entities/energy-electricity-data/energy-electricity-data.service';
import { EnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';

describe('Component Tests', () => {
    describe('EnergyElectricityData Management Component', () => {
        let comp: EnergyElectricityDataComponent;
        let fixture: ComponentFixture<EnergyElectricityDataComponent>;
        let service: EnergyElectricityDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyElectricityDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyElectricityDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyElectricityDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyElectricityDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyElectricityData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyElectricityData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
