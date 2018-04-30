import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Transmissao } from './transmissao.model';
import { TransmissaoPopupService } from './transmissao-popup.service';
import { TransmissaoService } from './transmissao.service';

@Component({
    selector: 'jhi-transmissao-delete-dialog',
    templateUrl: './transmissao-delete-dialog.component.html'
})
export class TransmissaoDeleteDialogComponent {

    transmissao: Transmissao;

    constructor(
        private transmissaoService: TransmissaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transmissaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'transmissaoListModification',
                content: 'Deleted an transmissao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transmissao-delete-popup',
    template: ''
})
export class TransmissaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private transmissaoPopupService: TransmissaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.transmissaoPopupService
                .open(TransmissaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
