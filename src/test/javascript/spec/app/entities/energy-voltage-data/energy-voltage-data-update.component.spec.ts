/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVoltageDataUpdateComponent } from 'app/entities/energy-voltage-data/energy-voltage-data-update.component';
import { EnergyVoltageDataService } from 'app/entities/energy-voltage-data/energy-voltage-data.service';
import { EnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';

describe('Component Tests', () => {
    describe('EnergyVoltageData Management Update Component', () => {
        let comp: EnergyVoltageDataUpdateComponent;
        let fixture: ComponentFixture<EnergyVoltageDataUpdateComponent>;
        let service: EnergyVoltageDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVoltageDataUpdateComponent]
            })
                .overrideTemplate(EnergyVoltageDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyVoltageDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVoltageDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyVoltageData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyVoltageData = entity;
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
                    const entity = new EnergyVoltageData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyVoltageData = entity;
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
