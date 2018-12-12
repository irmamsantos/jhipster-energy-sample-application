import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';
import { EnergyNeedNGRequestService } from './energy-need-ng-request.service';

@Component({
    selector: 'jhi-energy-need-ng-request-delete-dialog',
    templateUrl: './energy-need-ng-request-delete-dialog.component.html'
})
export class EnergyNeedNGRequestDeleteDialogComponent {
    energyNeedNGRequest: IEnergyNeedNGRequest;

    constructor(
        private energyNeedNGRequestService: EnergyNeedNGRequestService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyNeedNGRequestService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyNeedNGRequestListModification',
                content: 'Deleted an energyNeedNGRequest'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-need-ng-request-delete-popup',
    template: ''
})
export class EnergyNeedNGRequestDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyNeedNGRequest }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyNeedNGRequestDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyNeedNGRequest = energyNeedNGRequest;
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
