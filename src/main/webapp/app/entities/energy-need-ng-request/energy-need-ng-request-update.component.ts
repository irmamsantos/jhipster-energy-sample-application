import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';
import { EnergyNeedNGRequestService } from './energy-need-ng-request.service';
import { IUserSAC } from 'app/shared/model/user-sac.model';
import { UserSACService } from 'app/entities/user-sac';

@Component({
    selector: 'jhi-energy-need-ng-request-update',
    templateUrl: './energy-need-ng-request-update.component.html'
})
export class EnergyNeedNGRequestUpdateComponent implements OnInit {
    energyNeedNGRequest: IEnergyNeedNGRequest;
    isSaving: boolean;

    usersacs: IUserSAC[];
    updateDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private energyNeedNGRequestService: EnergyNeedNGRequestService,
        private userSACService: UserSACService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyNeedNGRequest }) => {
            this.energyNeedNGRequest = energyNeedNGRequest;
            this.updateDate =
                this.energyNeedNGRequest.updateDate != null ? this.energyNeedNGRequest.updateDate.format(DATE_TIME_FORMAT) : null;
        });
        this.userSACService.query().subscribe(
            (res: HttpResponse<IUserSAC[]>) => {
                this.usersacs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.energyNeedNGRequest.updateDate = this.updateDate != null ? moment(this.updateDate, DATE_TIME_FORMAT) : null;
        if (this.energyNeedNGRequest.id !== undefined) {
            this.subscribeToSaveResponse(this.energyNeedNGRequestService.update(this.energyNeedNGRequest));
        } else {
            this.subscribeToSaveResponse(this.energyNeedNGRequestService.create(this.energyNeedNGRequest));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyNeedNGRequest>>) {
        result.subscribe((res: HttpResponse<IEnergyNeedNGRequest>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserSACById(index: number, item: IUserSAC) {
        return item.id;
    }
}
