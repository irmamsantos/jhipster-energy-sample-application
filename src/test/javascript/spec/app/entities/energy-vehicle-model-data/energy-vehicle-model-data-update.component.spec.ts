/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleModelDataUpdateComponent } from 'app/entities/energy-vehicle-model-data/energy-vehicle-model-data-update.component';
import { EnergyVehicleModelDataService } from 'app/entities/energy-vehicle-model-data/energy-vehicle-model-data.service';
import { EnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';

describe('Component Tests', () => {
    describe('EnergyVehicleModelData Management Update Component', () => {
        let comp: EnergyVehicleModelDataUpdateComponent;
        let fixture: ComponentFixture<EnergyVehicleModelDataUpdateComponent>;
        let service: EnergyVehicleModelDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleModelDataUpdateComponent]
            })
                .overrideTemplate(EnergyVehicleModelDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyVehicleModelDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVehicleModelDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyVehicleModelData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyVehicleModelData = entity;
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
                    const entity = new EnergyVehicleModelData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyVehicleModelData = entity;
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
