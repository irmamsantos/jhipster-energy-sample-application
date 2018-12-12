/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { UserSACUpdateComponent } from 'app/entities/user-sac/user-sac-update.component';
import { UserSACService } from 'app/entities/user-sac/user-sac.service';
import { UserSAC } from 'app/shared/model/user-sac.model';

describe('Component Tests', () => {
    describe('UserSAC Management Update Component', () => {
        let comp: UserSACUpdateComponent;
        let fixture: ComponentFixture<UserSACUpdateComponent>;
        let service: UserSACService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [UserSACUpdateComponent]
            })
                .overrideTemplate(UserSACUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UserSACUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserSACService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UserSAC(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userSAC = entity;
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
                    const entity = new UserSAC();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userSAC = entity;
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
