import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';
import { EnergyContractedPowerRatingDataService } from './energy-contracted-power-rating-data.service';

@Component({
    selector: 'jhi-energy-contracted-power-rating-data-update',
    templateUrl: './energy-contracted-power-rating-data-update.component.html'
})
export class EnergyContractedPowerRatingDataUpdateComponent implements OnInit {
    energyContractedPowerRatingData: IEnergyContractedPowerRatingData;
    isSaving: boolean;

    constructor(
        private energyContractedPowerRatingDataService: EnergyContractedPowerRatingDataService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyContractedPowerRatingData }) => {
            this.energyContractedPowerRatingData = energyContractedPowerRatingData;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyContractedPowerRatingData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyContractedPowerRatingDataService.update(this.energyContractedPowerRatingData));
        } else {
            this.subscribeToSaveResponse(this.energyContractedPowerRatingDataService.create(this.energyContractedPowerRatingData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyContractedPowerRatingData>>) {
        result.subscribe(
            (res: HttpResponse<IEnergyContractedPowerRatingData>) => this.onSaveSuccess(),
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
}
