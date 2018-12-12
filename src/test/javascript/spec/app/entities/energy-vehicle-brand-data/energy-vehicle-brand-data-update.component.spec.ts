/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleBrandDataUpdateComponent } from 'app/entities/energy-vehicle-brand-data/energy-vehicle-brand-data-update.component';
import { EnergyVehicleBrandDataService } from 'app/entities/energy-vehicle-brand-data/energy-vehicle-brand-data.service';
import { EnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';

describe('Component Tests', () => {
    describe('EnergyVehicleBrandData Management Update Component', () => {
        let comp: EnergyVehicleBrandDataUpdateComponent;
        let fixture: ComponentFixture<EnergyVehicleBrandDataUpdateComponent>;
        let service: EnergyVehicleBrandDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleBrandDataUpdateComponent]
            })
                .overrideTemplate(EnergyVehicleBrandDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyVehicleBrandDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVehicleBrandDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyVehicleBrandData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyVehicleBrandData = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyVehicleBrandData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyVehicleBrandData = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
