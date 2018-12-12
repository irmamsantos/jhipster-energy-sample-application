/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNRStateHistoryUpdateComponent } from 'app/entities/energy-nr-state-history/energy-nr-state-history-update.component';
import { EnergyNRStateHistoryService } from 'app/entities/energy-nr-state-history/energy-nr-state-history.service';
import { EnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';

describe('Component Tests', () => {
    describe('EnergyNRStateHistory Management Update Component', () => {
        let comp: EnergyNRStateHistoryUpdateComponent;
        let fixture: ComponentFixture<EnergyNRStateHistoryUpdateComponent>;
        let service: EnergyNRStateHistoryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNRStateHistoryUpdateComponent]
            })
                .overrideTemplate(EnergyNRStateHistoryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyNRStateHistoryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyNRStateHistoryService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnergyNRStateHistory(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyNRStateHistory = entity;
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
                    const entity = new EnergyNRStateHistory();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.energyNRStateHistory = entity;
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
