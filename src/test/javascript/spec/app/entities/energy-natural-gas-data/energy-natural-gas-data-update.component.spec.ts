/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNaturalGasDataUpdateComponent } from 'app/entities/energy-natural-gas-data/energy-natural-gas-data-update.component';
import { EnergyNaturalGasDataService } from 'app/entities/energy-natural-gas-data/energy-natural-gas-data.service';
import { EnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';

describe('Component Tests', () => {
    describe('EnergyNaturalGasData Management Update Component', () => {
        let comp: EnergyNaturalGasDataUpdateComponent;
        let fixture: ComponentFixture<EnergyNaturalGasDataUpdateComponent>;
        let service: EnergyNaturalGasDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNaturalGasDataUpdateComponent]
            })
                .overrideTemplate(EnergyNaturalGasDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyNaturalGasDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyNaturalGasDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyNaturalGasData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyNaturalGasData = entity;
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
                    const entity = new EnergyNaturalGasData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyNaturalGasData = entity;
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
