/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyCycleDataUpdateComponent } from 'app/entities/energy-cycle-data/energy-cycle-data-update.component';
import { EnergyCycleDataService } from 'app/entities/energy-cycle-data/energy-cycle-data.service';
import { EnergyCycleData } from 'app/shared/model/energy-cycle-data.model';

describe('Component Tests', () => {
    describe('EnergyCycleData Management Update Component', () => {
        let comp: EnergyCycleDataUpdateComponent;
        let fixture: ComponentFixture<EnergyCycleDataUpdateComponent>;
        let service: EnergyCycleDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyCycleDataUpdateComponent]
            })
                .overrideTemplate(EnergyCycleDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyCycleDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyCycleDataService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyCycleData(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyCycleData = entity;
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
                    const entity = new EnergyCycleData();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyCycleData = entity;
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
