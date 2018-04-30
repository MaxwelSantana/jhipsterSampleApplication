import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Mapeamento } from './mapeamento.model';
import { MapeamentoPopupService } from './mapeamento-popup.service';
import { MapeamentoService } from './mapeamento.service';

@Component({
    selector: 'jhi-mapeamento-delete-dialog',
    templateUrl: './mapeamento-delete-dialog.component.html'
})
export class MapeamentoDeleteDialogComponent {

    mapeamento: Mapeamento;

    constructor(
        private mapeamentoService: MapeamentoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mapeamentoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'mapeamentoListModification',
                content: 'Deleted an mapeamento'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mapeamento-delete-popup',
    template: ''
})
export class MapeamentoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mapeamentoPopupService: MapeamentoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.mapeamentoPopupService
                .open(MapeamentoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
