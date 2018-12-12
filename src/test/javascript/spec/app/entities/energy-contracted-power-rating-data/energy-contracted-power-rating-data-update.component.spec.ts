/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyContractedPowerRatingDataUpdateComponent } from 'app/entities/energy-contracted-power-rating-data/energy-contracted-power-rating-data-update.component';
import { EnergyContractedPowerRatingDataService } from 'app/entities/energy-contracted-power-rating-data/energy-contracted-power-rating-data.service';
import { EnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';

describe('Component Tests', () => {
    describe('EnergyContractedPowerRatingData Management Update Component', () => {
        let comp: EnergyContractedPowerRatingDataUpdateComponent;
        let fixture: ComponentFixture<EnergyContractedPowerRatingDataUpdateComponent>;
        let service: EnergyContractedPowerRatingDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyContractedPowerRatingDataUpdateComponent]
            })
                .overrideTemplate(EnergyContractedPowerRatingDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyContractedPowerRatingDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyContractedPowerRatingDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyContractedPowerRatingData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyContractedPowerRatingData = entity;
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
                    const entity = new EnergyContractedPowerRatingData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyContractedPowerRatingData = entity;
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
