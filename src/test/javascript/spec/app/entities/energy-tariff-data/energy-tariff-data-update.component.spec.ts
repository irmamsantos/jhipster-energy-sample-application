/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyTariffDataUpdateComponent } from 'app/entities/energy-tariff-data/energy-tariff-data-update.component';
import { EnergyTariffDataService } from 'app/entities/energy-tariff-data/energy-tariff-data.service';
import { EnergyTariffData } from 'app/shared/model/energy-tariff-data.model';

describe('Component Tests', () => {
    describe('EnergyTariffData Management Update Component', () => {
        let comp: EnergyTariffDataUpdateComponent;
        let fixture: ComponentFixture<EnergyTariffDataUpdateComponent>;
        let service: EnergyTariffDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyTariffDataUpdateComponent]
            })
                .overrideTemplate(EnergyTariffDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyTariffDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyTariffDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyTariffData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyTariffData = entity;
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
                    const entity = new EnergyTariffData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyTariffData = entity;
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
