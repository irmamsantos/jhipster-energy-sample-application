/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNeedNGRequestUpdateComponent } from 'app/entities/energy-need-ng-request/energy-need-ng-request-update.component';
import { EnergyNeedNGRequestService } from 'app/entities/energy-need-ng-request/energy-need-ng-request.service';
import { EnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';

describe('Component Tests', () => {
    describe('EnergyNeedNGRequest Management Update Component', () => {
        let comp: EnergyNeedNGRequestUpdateComponent;
        let fixture: ComponentFixture<EnergyNeedNGRequestUpdateComponent>;
        let service: EnergyNeedNGRequestService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNeedNGRequestUpdateComponent]
            })
                .overrideTemplate(EnergyNeedNGRequestUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyNeedNGRequestUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyNeedNGRequestService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyNeedNGRequest(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyNeedNGRequest = entity;
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
                    const entity = new EnergyNeedNGRequest();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyNeedNGRequest = entity;
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
