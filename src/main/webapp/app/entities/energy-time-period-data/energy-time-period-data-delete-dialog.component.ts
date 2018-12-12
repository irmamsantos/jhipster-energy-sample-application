import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';
import { EnergyTimePeriodDataService } from './energy-time-period-data.service';

@Component({
    selector: 'jhi-energy-time-period-data-delete-dialog',
    templateUrl: './energy-time-period-data-delete-dialog.component.html'
})
export class EnergyTimePeriodDataDeleteDialogComponent {
    energyTimePeriodData: IEnergyTimePeriodData;

    constructor(
        private energyTimePeriodDataService: EnergyTimePeriodDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyTimePeriodDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyTimePeriodDataListModification',
                content: 'Deleted an energyTimePeriodData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-time-period-data-delete-popup',
    template: ''
})
export class EnergyTimePeriodDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyTimePeriodData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyTimePeriodDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyTimePeriodData = energyTimePeriodData;
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
