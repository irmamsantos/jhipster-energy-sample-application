import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';
import { EnergyContractedPowerRatingDataService } from './energy-contracted-power-rating-data.service';

@Component({
    selector: 'jhi-energy-contracted-power-rating-data-delete-dialog',
    templateUrl: './energy-contracted-power-rating-data-delete-dialog.component.html'
})
export class EnergyContractedPowerRatingDataDeleteDialogComponent {
    energyContractedPowerRatingData: IEnergyContractedPowerRatingData;

    constructor(
        private energyContractedPowerRatingDataService: EnergyContractedPowerRatingDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyContractedPowerRatingDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyContractedPowerRatingDataListModification',
                content: 'Deleted an energyContractedPowerRatingData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-contracted-power-rating-data-delete-popup',
    template: ''
})
export class EnergyContractedPowerRatingDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyContractedPowerRatingData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyContractedPowerRatingDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyContractedPowerRatingData = energyContractedPowerRatingData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
