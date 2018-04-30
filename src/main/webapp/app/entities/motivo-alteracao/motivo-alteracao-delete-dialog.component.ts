import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MotivoAlteracao } from './motivo-alteracao.model';
import { MotivoAlteracaoPopupService } from './motivo-alteracao-popup.service';
import { MotivoAlteracaoService } from './motivo-alteracao.service';

@Component({
    selector: 'jhi-motivo-alteracao-delete-dialog',
    templateUrl: './motivo-alteracao-delete-dialog.component.html'
})
export class MotivoAlteracaoDeleteDialogComponent {

    motivoAlteracao: MotivoAlteracao;

    constructor(
        private motivoAlteracaoService: MotivoAlteracaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.motivoAlteracaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'motivoAlteracaoListModification',
                content: 'Deleted an motivoAlteracao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-motivo-alteracao-delete-popup',
    template: ''
})
export class MotivoAlteracaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private motivoAlteracaoPopupService: MotivoAlteracaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.motivoAlteracaoPopupService
                .open(MotivoAlteracaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
