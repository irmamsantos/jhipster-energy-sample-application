/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyElectricityDataUpdateComponent } from 'app/entities/energy-electricity-data/energy-electricity-data-update.component';
import { EnergyElectricityDataService } from 'app/entities/energy-electricity-data/energy-electricity-data.service';
import { EnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';

describe('Component Tests', () => {
    describe('EnergyElectricityData Management Update Component', () => {
        let comp: EnergyElectricityDataUpdateComponent;
        let fixture: ComponentFixture<EnergyElectricityDataUpdateComponent>;
        let service: EnergyElectricityDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyElectricityDataUpdateComponent]
            })
                .overrideTemplate(EnergyElectricityDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyElectricityDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyElectricityDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyElectricityData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyElectricityData = entity;
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
                    const entity = new EnergyElectricityData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyElectricityData = entity;
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
