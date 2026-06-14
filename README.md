# portal stack module

- Module id: `portal`
- Module repo: `portal-stack-module`
- Source repo: `portal`
- Lifecycle: `active`

## Owned overlays
- `stack.compose/portal.yml`
- `stack.containers/portal`
- `stack.kotlin/portal`

## Dependencies
- `stack-foundation`

## Validation

```sh
./tests/validate.sh
```

## Lifecycle

`active` modules are expected to keep `stack.module.json`, owned overlays, and `tests/validate.sh` in sync.
