import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyCycleData } from 'app/shared/model/energy-cycle-data.model';
import { EnergyCycleDataService } from './energy-cycle-data.service';

@Component({
    selector: 'jhi-energy-cycle-data-update',
    templateUrl: './energy-cycle-data-update.component.html'
})
export class EnergyCycleDataUpdateComponent implements OnInit {
    energyCycleData: IEnergyCycleData;
    isSaving: boolean;

    constructor(private energyCycleDataService: EnergyCycleDataService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyCycleData }) => {
            this.energyCycleData = energyCycleData;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyCycleData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyCycleDataService.update(this.energyCycleData));
        } else {
            this.subscribeToSaveResponse(this.energyCycleDataService.create(this.energyCycleData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyCycleData>>) {
        result.subscribe((res: HttpResponse<IEnergyCycleData>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
