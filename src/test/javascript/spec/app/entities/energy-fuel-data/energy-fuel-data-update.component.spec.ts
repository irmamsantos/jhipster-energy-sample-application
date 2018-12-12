/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyFuelDataUpdateComponent } from 'app/entities/energy-fuel-data/energy-fuel-data-update.component';
import { EnergyFuelDataService } from 'app/entities/energy-fuel-data/energy-fuel-data.service';
import { EnergyFuelData } from 'app/shared/model/energy-fuel-data.model';

describe('Component Tests', () => {
    describe('EnergyFuelData Management Update Component', () => {
        let comp: EnergyFuelDataUpdateComponent;
        let fixture: ComponentFixture<EnergyFuelDataUpdateComponent>;
        let service: EnergyFuelDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyFuelDataUpdateComponent]
            })
                .overrideTemplate(EnergyFuelDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyFuelDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyFuelDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyFuelData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyFuelData = entity;
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
                    const entity = new EnergyFuelData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyFuelData = entity;
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
