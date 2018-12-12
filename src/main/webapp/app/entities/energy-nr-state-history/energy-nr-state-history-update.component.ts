import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';
import { EnergyNRStateHistoryService } from './energy-nr-state-history.service';
import { IEnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';
import { EnergyNeedNGRequestService } from 'app/entities/energy-need-ng-request';
import { IUserSAC } from 'app/shared/model/user-sac.model';
import { UserSACService } from 'app/entities/user-sac';

@Component({
    selector: 'jhi-energy-nr-state-history-update',
    templateUrl: './energy-nr-state-history-update.component.html'
})
export class EnergyNRStateHistoryUpdateComponent implements OnInit {
    energyNRStateHistory: IEnergyNRStateHistory;
    isSaving: boolean;

    energyneedngrequests: IEnergyNeedNGRequest[];

    usersacs: IUserSAC[];
    updateDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private energyNRStateHistoryService: EnergyNRStateHistoryService,
        private energyNeedNGRequestService: EnergyNeedNGRequestService,
        private userSACService: UserSACService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyNRStateHistory }) => {
            this.energyNRStateHistory = energyNRStateHistory;
            this.updateDate =
                this.energyNRStateHistory.updateDate != null ? this.energyNRStateHistory.updateDate.format(DATE_TIME_FORMAT) : null;
        });
        this.energyNeedNGRequestService.query().subscribe(
            (res: HttpResponse<IEnergyNeedNGRequest[]>) => {
                this.energyneedngrequests = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        this.energyNRStateHistory.updateDate = this.updateDate != null ? moment(this.updateDate, DATE_TIME_FORMAT) : null;
        if (this.energyNRStateHistory.id !== undefined) {
            this.subscribeToSaveResponse(this.energyNRStateHistoryService.update(this.energyNRStateHistory));
        } else {
            this.subscribeToSaveResponse(this.energyNRStateHistoryService.create(this.energyNRStateHistory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyNRStateHistory>>) {
        result.subscribe(
            (res: HttpResponse<IEnergyNRStateHistory>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackEnergyNeedNGRequestById(index: number, item: IEnergyNeedNGRequest) {
        return item.id;
    }

    trackUserSACById(index: number, item: IUserSAC) {
        return item.id;
    }
}
