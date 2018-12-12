import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUserSAC } from 'app/shared/model/user-sac.model';
import { UserSACService } from './user-sac.service';

@Component({
    selector: 'jhi-user-sac-update',
    templateUrl: './user-sac-update.component.html'
})
export class UserSACUpdateComponent implements OnInit {
    userSAC: IUserSAC;
    isSaving: boolean;
    updateDate: string;

    constructor(private userSACService: UserSACService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userSAC }) => {
            this.userSAC = userSAC;
            this.updateDate = this.userSAC.updateDate != null ? this.userSAC.updateDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.userSAC.updateDate = this.updateDate != null ? moment(this.updateDate, DATE_TIME_FORMAT) : null;
        if (this.userSAC.id !== undefined) {
            this.subscribeToSaveResponse(this.userSACService.update(this.userSAC));
        } else {
            this.subscribeToSaveResponse(this.userSACService.create(this.userSAC));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUserSAC>>) {
        result.subscribe((res: HttpResponse<IUserSAC>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
