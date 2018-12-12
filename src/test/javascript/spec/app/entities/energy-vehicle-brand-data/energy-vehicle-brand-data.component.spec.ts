/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleBrandDataComponent } from 'app/entities/energy-vehicle-brand-data/energy-vehicle-brand-data.component';
import { EnergyVehicleBrandDataService } from 'app/entities/energy-vehicle-brand-data/energy-vehicle-brand-data.service';
import { EnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';

describe('Component Tests', () => {
    describe('EnergyVehicleBrandData Management Component', () => {
        let comp: EnergyVehicleBrandDataComponent;
        let fixture: ComponentFixture<EnergyVehicleBrandDataComponent>;
        let service: EnergyVehicleBrandDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleBrandDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyVehicleBrandDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyVehicleBrandDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVehicleBrandDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyVehicleBrandData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyVehicleBrandData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
